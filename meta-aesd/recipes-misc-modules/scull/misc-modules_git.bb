# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-wlbe4.git;protocol=ssh;branch=main \
           file://0001-Build-only-misc-modules-and-scull.patch \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "38b0cd43c259a7dffdf452b801ce662b0c0316de"

S = "${WORKDIR}/git"

inherit module

EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"
EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/misc-modules"
