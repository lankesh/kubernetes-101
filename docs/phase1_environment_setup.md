# Phase 1 Environment Setup

This document details the steps required to set up your development environment for the `kubernetes-101` project. In this phase, you will configure everything you need to build, containerize, and deploy a Spring Boot application (with real-time AI call simulation) on a local Kubernetes cluster using Kind.

## Table of Contents

- [Installing OpenJDK](#installing-openjdk)
- [Installing VSCodium](#installing-vscodium)
- [Installing Docker Desktop](#installing-docker-desktop)
- [Installing Kind](#installing-kind)
- [Verifying the Environment](#verifying-the-environment)
- [Additional Tips](#additional-tips)

## Installing OpenJDK

**Download OpenJDK:**

Visit the [Adoptium website](https://adoptium.net/) and choose OpenJDK 11 or OpenJDK 21 (or later). Download the installer for your operating system.

**Install OpenJDK:**

Follow the instructions provided on the Adoptium site for installation.

**Verify Installation:**

Open a terminal and run:

```bash
java -version
```

You should see output indicating the installed JDK version.

## Installing VSCodium

**Download VSCodium:**

Go to the [VSCodium releases page](https://github.com/VSCodium/vscodium/releases) and download the installer appropriate for your OS.

**Install VSCodium:**

Follow the installation prompts.

**Configure VSCodium for Java:**

Open VSCodium and go to the Extensions view.

Install the following extensions:
- Language Support for Java™ by Red Hat
- Debugger for Java
- Java Test Runner (optional, for running tests)
- Gradle for Java (optional, for Gradle project support)

Configure VSCodium to use your OpenJDK by adding the following in your settings (accessed via Ctrl+, or File > Preferences > Settings):

```json
{
  "java.home": "/path/to/openjdk"
}
```

Replace `/path/to/openjdk` with the actual path where OpenJDK is installed on your system.

## Installing Docker Desktop

**Download Docker Desktop:**

Visit the [Docker Desktop](https://www.docker.com/products/docker-desktop) page and download the installer for your OS.

**Install Docker Desktop:**

Follow the on-screen instructions to complete the installation.

**Verify Docker Installation:**

Open a terminal and run:

```bash
docker run hello-world
```

This command should pull a test image and display a success message if Docker is installed correctly.

## Installing Kind

**Install Kind:**

Follow the [Kind Quick Start Guide](https://kind.sigs.k8s.io/docs/user/quick-start/).

On many systems, you can install Kind using a package manager:
- On macOS with Homebrew:
  ```bash
  brew install kind
  ```
- On Windows using Chocolatey:
  ```bash
  choco install kind
  ```

**Verify Kind Installation:**

Open a terminal and run:

```bash
kind version
```

You should see the Kind version printed out.

**Create a Sample Cluster:**

Create a basic cluster with:

```bash
kind create cluster --name kubernetes-101-cluster
```

Verify the cluster is running:

```bash
kubectl get nodes
```

## Verifying the Environment

After installing all components, perform the following checks:

**Java:**

Run `java -version` to confirm OpenJDK is installed.

**VSCodium:**

Open your project in VSCodium; ensure Java extensions are active.

**Docker:**

Run `docker run hello-world` to verify Docker is working.

**Kind/Kubernetes:**

Create and verify a cluster with:

```bash
kind create cluster --name kubernetes-101-cluster
kubectl get nodes
```

## Additional Tips

### Environment Variables

Ensure your system's PATH includes the paths to OpenJDK, Docker, and Kind.

### Resource Allocation

For Docker Desktop and Kind, allocate at least 4GB of RAM to ensure smooth operation.

### Documentation & Community

Bookmark the official documentation pages for Docker, Kubernetes, and VSCodium. Join related forums or Slack channels to get support and updates.
