## 1. Build the Docker Image

First, ensure your Spring Boot application is packaged as a JAR file. If you're using Gradle, you can build the project with:

```bash
./gradlew build
```

This command will generate a JAR file in the `build/libs/` directory.

Next, build the Docker image using your Dockerfile. Navigate to the directory containing your Dockerfile and run:

```bash
docker build -t aicallsimulator:latest .
```

This command creates a Docker image tagged as `aicallsimulator:latest`.

## 2. Push the Docker Image to a Local Registry

To allow your local Kubernetes cluster to access your Docker image, set up a local Docker registry. Start the registry container:

```bash
docker run -d -p 5000:5000 --name kind-registry registry:2
```

Tag your Docker image to match the local registry's address:

```bash
docker tag aicallsimulator:latest localhost:5000/aicallsimulator:latest
```

Push the image to the local registry:

```bash
docker push localhost:5000/aicallsimulator:latest
```

## 3. Set Up a Local Kubernetes Cluster with Kind

Create a Kind configuration file (`kind-config.yaml`) to connect the Kind cluster to the local Docker registry:

```yaml
kind: Cluster
apiVersion: kind.x-k8s.io/v1alpha4
containerdConfigPatches:
- |-
  [plugins."io.containerd.grpc.v1.cri".registry.mirrors."localhost:5000"]
    endpoint = ["http://kind-registry:5000"]
nodes:
  - role: control-plane
    extraPortMappings:
    - containerPort: 30000
      hostPort: 8080
```

This configuration sets up the cluster to use the local registry and maps port 8080 on your host to port 30000 on the cluster.

Create the Kind cluster using this configuration:

```bash
kind create cluster --name aicallsimulator-cluster --config kind-config.yaml
```

Connect the Kind network to the local registry:

```bash
docker network connect "kind" "kind-registry"
```

## 4. Deploy the Application to Kubernetes

Create a Kubernetes Deployment manifest (`deployment.yaml`):

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: aicallsimulator-deployment
spec:
  replicas: 3
  selector:
    matchLabels:
      app: aicallsimulator
  template:
    metadata:
      labels:
        app: aicallsimulator
    spec:
      containers:
        - name: aicallsimulator
          image: localhost:5000/aicallsimulator:latest
          ports:
            - containerPort: 8080
```

This manifest defines a Deployment that runs one replica of your application.

Create a Kubernetes Service manifest (`service.yaml`):

```yaml
apiVersion: v1
kind: Service
metadata:
  name: aicallsimulator-service
spec:
  selector:
    app: aicallsimulator
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
  type: LoadBalancer  # Kind translates this to NodePort
```

This Service exposes your application on port 8080 and maps it to port 30000 on the node.

Apply these manifests to your Kind cluster:

```bash
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```

## 5. Access the Application

Your application should now be accessible at `http://localhost:8080`. You can verify this by sending a request to your application's endpoint:

```bash
curl http://localhost:8080/api/simulateCall
```

This command should return a response from your AI call simulation application.

## 6. Verify the Deployment
Check if your pods and services are running

```bash
kublectl get pods # Should show 3 pods in "Running" state
kubectl get services  # Look for the aicallsimulator-service
```

Access the Application
Since LoadBalancer in Kind uses NodePort, use kubectl port-forward to access it:

```bash
kubectl port-forward service/aicallsimulator-service 8080:80
```

## 7. Clean up
When done, delete the cluster:

```bash
kind delete cluster --name aicallsimulator-cluster
```

To delete kind registry:
```bash
docker stop kind-registry
docker rm kind-registry
```

To remove kind registry docker image:
```bash
docker rmi localhost:5000/aicallsimulator:latest
```
