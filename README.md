# meta-heipc examples

This repo shows some of the functionality that heipc (hyper easy inter-process-communication) provides.

Do not forget to include the current version of meta-heipc via submodule:

```bash
git submodule update --init
```

## Building the Image

We are using [siemens/kas](https://github.com/siemens/kas) for building, install it and then simply build the entire image with:

```bash
kas build kas-project.yml
```

## SDK

Build the SDK package with `populate_sdk` command:

```bash
kas shell kas-project.yml -c "bitbake -c populate_sdk heipc-example-image"
```

Install it easily with:

```bash
./build/tmp/deploy/sdk/poky-glibc-aarch64-heipc-example-image-cortexa57-qemuarm64-toolchain-5.0.3.sh
```

## Using the Target-SDK

## Using the Native-SDK

One of the major examples of using GRPC for IPC is that it actually uses network for communication.
This means it is easier to debug from a development PC allowing for additional external tooling.

To demonstrate this we are going to use the nativesdk packages provided by yocto.

## MacOS

Developing on MacOS is a little more complicated for several reasons.
Here is some information on how to workaround the common issues:

To tackle the case-sensitive (and other) filesystem issues, create a volume for build:

```bash
docker volume create heipc-build
```

Then open the project in vscode, using the provided devcontainer and follow the build steps as before.
There is a rsync alias `sync_results` which will rsync images and sdk from deploy to local.