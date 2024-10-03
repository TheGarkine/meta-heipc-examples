DESCRIPTION = "Show the latest build time in shell on startup"
LICENSE = "MIT"

inherit allarch

do_install() {
    mkdir -p ${D}${sysconfdir}
    mkdir -p ${D}/etc/profile.d

    # Get the current build time and write it to a file
    echo "Build time: $(date)" > ${D}${sysconfdir}/buildtime

    # Add sourcing command to the bashrc or shell startup script
    echo 'cat /etc/buildtime' >> ${D}/etc/profile.d/show-build-time.sh
}

FILES:${PN} += "${sysconfdir}/buildtime"