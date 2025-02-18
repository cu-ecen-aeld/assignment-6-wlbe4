# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-wlbe4.git;protocol=ssh;branch=main \
           file://0001-Modify-Makefile.patch \
           file://S98lddmodules \ 
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "38b0cd43c259a7dffdf452b801ce662b0c0316de"

S = "${WORKDIR}/git"

inherit module

EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"
EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/misc-modules"
EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/scull"
#MODULES_MODULE_SYMVERS_LOCATION:append:task-install = " ${S}/misc-modules"
#MODULES_MODULE_SYMVERS_LOCATION:append:task-install = " ${S}/scull"

FILES:${PN} += "${sysconfdir}/init.d/*"
FILES:${PN} += "${sysconfdir}/rcS.d/*"

do_install () {
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rcS.d
    install -m 0755 ${WORKDIR}/S98lddmodules        ${D}${sysconfdir}/rcS.d
	install -m 0755 ${S}/scull/scull_load 		    ${D}${sysconfdir}/init.d
	install -m 0755 ${S}/scull/scull_unload 		${D}${sysconfdir}/init.d
    install -m 0755 ${S}/misc-modules/module_load 	${D}${sysconfdir}/init.d
	install -m 0755 ${S}/misc-modules/module_unload ${D}${sysconfdir}/init.d
    module_do_install
}