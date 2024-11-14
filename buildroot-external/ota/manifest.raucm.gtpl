[update]
compatible={{ env "ota_compatible" }}
version={{ env "ota_version" }}

[bundle]
format=verity

[hooks]
filename=hook
hooks=install-check;

[image.boot]
filename=boot.vfat
{{- if eq (env "ota_compatible") "haos-yellow" }}
hooks=install;post-install;
{{- else }}
hooks=install;
{{- end }}

[image.kernel]
filename=kernel.img
{{- $bootloader := (env "BOOTLOADER") }}
{{- if or (eq $bootloader "grub") (eq $bootloader "tryboot") }}
hooks=post-install;
{{- end }}

[image.rootfs]
filename=rootfs.img

{{- if eq (env "BOOT_SPL") "true" }}
[image.spl]
filename=spl.img
hooks=install
{{- end }}
