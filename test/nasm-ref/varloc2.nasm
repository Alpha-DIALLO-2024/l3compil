%include	'io.asm'

section	.bss
sinput:	resb	255	;reserve a 255 byte space in memory for the users input string

section	.text
global _start
_start:
	call	main	;
	mov	ebx,	0	; valeur de retour du programme
	mov	eax,	1	;
	int 0x80	;
main :	push	ebp	;sauvegarde la valeur de ebp
	mov	ebp,	esp	;nouvelle valeur de ebp
	sub	esp,	4	;allocation des variables locales
	mov	dword [ebp-4*1],	10	;Affect
	mov	eax,	dword [ebp-4*1]	;
	add	eax,	50	;
	mov	eax,	eax	;Write 1
	call	iprintLF	;Write 2
	add	esp,	4	;désallocation des variables locales
	pop	ebp	;restaure la valeur de ebp
	ret	;
