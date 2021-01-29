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
	sub	esp,	0	;allocation des variables locales
	mov	ecx,	1	;Affect
	mov	ebx,	0	;JumpIfEqual 1
	cmp	ebx,	0	;on passe par un registre temporaire
	je	l0	;JumpIfEqual 2
	mov	ecx,	0	;Affect
l0 :	mov	eax,	ecx	;
	mov	ebx,	1	;
	idiv	ebx	;
	mov	eax,	eax	;
	mov	eax,	eax	;Write 1
	call	iprintLF	;Write 2
	add	esp,	0	;désallocation des variables locales
	pop	ebp	;restaure la valeur de ebp
	ret	;
