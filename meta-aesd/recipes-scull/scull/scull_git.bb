# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-wlbe4.git;protocol=ssh;branch=main \
           file://0001-Build-only-misc-modules-and-scull.patch \
           "
SRC_URI += " file://S98lddmodules "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "38b0cd43c259a7dffdf452b801ce662b0c0316de"

S = "${WORKDIR}/git"

inherit module

EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"
#EXTRA_OEMAKE += " -C ${STAGING_KERNEL_DIR} M=${S}/scull"
EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/scull"
#EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/misc-modules"

#inherit update-rc.d
#INITSCRIPT_PACKAGES = "${PN}"
#INITSCRIPT_NAME:${PN} = "S98lddmodules"
#pkg_postinst_ontarget:${PN} () {
#    
#}

#INITSCRIPT_PACKAGES = "${PN}"
## 
##FILES_${PN} += "/usr/lib/modules/${KERNEL_VERSION}/extra/scull.ko"
#FILES:${PN} += "${D}${sysconfdir}/init.d/S98lddmodules"
#
#do_install () {
#    install -d ${D}${sysconfdir}/init.d
#    install -m 0755 ${BASE_WORKDIR}/../../../meta-aesd/recipes-scull/scull/files/S98lddmodules 			${D}${sysconfdir}/init.d
####	 install -m 0755 ${S}/scull/scull_unload 		${D}${sysconfdir}/init.d
####    install -m 0755 ${S}/misc-modules/module_load 	${D}${sysconfdir}/init.d
####	install -m 0755 ${S}/misc-modules/module_unload ${D}${sysconfdir}/init.d
###   install -d ${D}${libdir}/modules/${KERNEL_VERSION}/extra
###   install -m 0644 ${S}/scull/scull.ko ${D}${libdir}/modules/${KERNEL_VERSION}/extra
###    install -m 0644 tmp/work/qemuarm64-poky-linux/scull/1.0+gitAUTOINC+38b0cd43c2-r0/git/misc-modules/faulty.ko ${D}${libdir}/modules/${KERNEL_VERSION}/extra
#}
