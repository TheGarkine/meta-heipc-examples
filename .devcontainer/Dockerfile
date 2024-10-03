FROM ubuntu:22.04
LABEL maintainer="Raphael Krauthann <krauthann1@googlemail.com>"

# Set non-interactive frontend for apt-get
ENV DEBIAN_FRONTEND=noninteractive
ENV LANG=en_US.UTF-8
ENV LANGUAGE=en_US:en
ENV LC_ALL=en_US.UTF-8

RUN apt-get update && apt-get upgrade -y
RUN apt-get install -y \
    # Install dependencies for yocto
        chrpath \
        cpio \
        diffstat \
        file \
        gawk \
        kas \
        locales \
        locales-all \
        lz4 \
        python3 \
        python3-pip \
        sudo \
        u-boot-tools \
        wget \
        zstd \
    # Install qemu tooling
        qemu-system-arm \
        qemu-utils \
    # Install tools
        curl \
        iputils-ping \
        mtd-utils \
        nano \
        net-tools \
        rsync \
        ssh \
        screen \
        xz-utils && \
    apt-get clean && \
    apt-get autoremove -y && \
    rm -rf /var/lib/apt/lists/* && \
    locale-gen en_US.UTF-8
# Add user, user's home, set passwords
RUN useradd -m -G adm,sudo,users --uid=1000 -s /bin/bash kas && \
    install -d -o kas -g kas /home/kas && \
    echo 'kas:kas' | chpasswd && \
    echo 'root:root' | chpasswd && \
# Add mountpoint for sources, add extra mountpoint used for MACOS builds
    mkdir -p /sources && chown -R kas:kas /sources && \
    mkdir -p /build/tmp/deploy && chown -R kas:kas /build && \
# Add some settings (avoid ADD of custome .bashrc due to extra layer)
    echo "alias ll='ls -la'"                       >> /home/kas/.bashrc \
    echo "alias sync_results='mkdir -p build/tmp/deploy && rsync -a --progress --delete /build/tmp/deploy/images/ ./build/tmp/deploy/images && rsync -a --progress --delete /build/tmp/deploy/sdk/ ./build/tmp/deploy/sdk'"                   >> /home/kas/.bashrc \

USER kas
WORKDIR /sources