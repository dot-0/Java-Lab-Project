/*
 * stat.h
 * This file has no copyright assigned and is placed in the Public Domain.
 * This file is a part of the mingw-runtime package.
 * No warranty is given; refer to the file DISCLAIMER within the package.
 *
 * Symbolic constants for opening and creating files, also stat, fstat and
 * chmod functions.
 *
 */

#ifndef _STAT_H_
#define _STAT_H_

/* All the headers include this file. */
#include <_mingw.h>

#define __need_size_t
#define __need_wchar_t
#ifndef RC_INVOKED
#include <stddef.h>
#endif /* Not RC_INVOKED */

#include <sys/types.h>

/*
 * Constants for the stat st_mode member.
 */
#define	_S_IFIFO	0x1000	/* FIFO */
#define	_S_IFCHR	0x2000	/* Character */
#define	_S_IFBLK	0x3000	/* Block: Is this ever set under w32? */
#define	_S_IFDIR	0x4000	/* Directory */
#define	_S_IFREG	0x8000	/* Regular */

#define	_S_IFMT		0xF000	/* File type mask */

#define	_S_IEXEC	0x0040
#define	_S_IWRITE	0x0080
#define	_S_IREAD	0x0100

#define	_S_IRWXU	(_S_IREAD | _S_IWRITE | _S_IEXEC)
#define	_S_IXUSR	_S_IEXEC
#define	_S_IWUSR	_S_IWRITE
#define	_S_IRUSR	_S_IREAD

#define	_S_ISDIR(m)	(((m) & _S_IFMT) == _S_IFDIR)
#define	_S_ISFIFO(m)	(((m) & _S_IFMT) == _S_IFIFO)
#define	_S_ISCHR(m)	(((m) & _S_IFMT) == _S_IFCHR)
#define	_S_ISBLK(m)	(((m) & _S_IFMT) == _S_IFBLK)
#define	_S_ISREG(m)	(((m) & _S_IFMT) == _S_IFREG)

#ifndef _NO_OLDNAMES

#define	S_IFIFO		_S_IFIFO
#define	S_IFCHR		_S_IFCHR
#define	S_IFBLK		_S_IFBLK
#define	S_IFDIR		_S_IFDIR
#define	S_IFREG		_S_IFREG
#define	S_IFMT		_S_IFMT
#define	S_IEXEC		_S_IEXEC
#define	S_IWRITE	_S_IWRITE
#define	S_IREAD		_S_IREAD
#define	S_IRWXU		_S_IRWXU
#define	S_IXUSR		_S_IXUSR
#define	S_IWUSR		_S_IWUSR
#define	S_IRUSR		_S_IRUSR

#define	S_ISDIR(m)	(((m) & S_IFMT) == S_IFDIR)
#define	S_ISFIFO(m)	(((m) & S_IFMT) == S_IFIFO)
#define	S_ISCHR(m)	(((m) & S_IFMT) == S_IFCHR)
#define	S_ISBLK(m)	(((m) & S_IFMT) == S_IFBLK)
#define	S_ISREG(m)	(((m) & S_IFMT) == S_IFREG)

#endif	/* Not _NO_OLDNAMES */

#ifndef RC_INVOKED

#ifndef _STAT_DEFINED
/*
 * The structure manipulated and returned by stat and fstat.
 *
 * NOTE: If called on a directory the values in the time fields are not only
 * invalid, they will cause localtime et. al. to return NULL. And calling
 * asctime with a NULL pointer causes an Invalid Page Fault. So watch it!
 */
struct _stat
{
	_dev_t	st_dev;		/* Equivalent to drive number 0=A 1=B ... */
	_ino_t	st_ino;		/* Always zero ? */
	_mode_t	st_mode;	/* See above constants */
	short	st_nlink;	/* Number of links. */
	short	st_uid;		/* User: Maybe significant on NT ? */
	short	st_gid;		/* Group: Ditto */
	_dev_t	st_rdev;	/* Seems useless (not even filled in) */
	_off_t	st_size;	/* File size in bytes */
	time_t	st_atime;	/* Accessed date (always 00:00 hrs local
				 * on FAT) */
	time_t	st_mtime;	/* Modified time */
	time_t	st_ctime;	/* Creation time */
};

#ifndef	_NO_OLDNAMES
/* NOTE: Must be the same as _stat above. */
struct stat
{
	dev_t	st_dev;		/* Equivalent to drive number 0=A 1=B ... */
	ino_t	st_ino;		/* Always zero ? */
	mode_t	st_mode;	/* See above constants */
	short	st_nlink;	/* Number of links. */
	short	st_uid;		/* User: Maybe significant on NT ? */
	short	st_gid;		/* Group: Ditto */
	dev_t	st_rdev;	/* Seems useless (not even filled in) */
	off_t	st_size;	/* File size in bytes */
	time_t	st_atime;	/* Accessed date (always 00:00 hrs local
				 * on FAT) */
	time_t	st_mtime;	/* Modified time */
	time_t	st_ctime;	/* Creation time */
};
#endif /* _NO_OLDNAMES */

#if defined (__MSVCRT__)
struct _stati64 {
    _dev_t st_dev;
    _ino_t st_ino;
    _mode_t st_mode;
    short st_nlink;
    short st_uid;
    short st_gid;
    _dev_t st_rdev;
    __int64 st_size;
    time_t st_atime;
    time_t st_mtime;
    time_t st_ctime;
};
#if __MSVCRT_VERSION__ >= 0x0601
struct __stat64
{
    _dev_t st_dev;
    _ino_t st_ino;
    _mode_t st_mode;
    short st_nlink;
    short st_uid;
    short st_gid;
    _dev_t st_rdev;
    __int64 st_size;
    __time64_t st_atime;
    __time64_t st_mtime;
    __time64_t st_ctime;
};
#endif /* __MSVCRT_VERSION__ >= 0x0601 */
#if __MSVCRT_VERSION__ >= 0x0800
struct __stat32
{
	_dev_t		st_dev;
	_ino_t		st_ino;
	_mode_t		st_mode;
	short		st_nlink;
	short		st_uid;
	short		st_gid;
	_dev_t		st_rdev;
	__int32		st_size;
	__time32_t	st_atime;
	__time32_t	st_mtime;
	__time32_t	st_ctime;
};
struct _stat32i64 {
	_dev_t		st_dev;
	_ino_t		st_ino;
	_mode_t		st_mode;
	short		st_nlink;
	short		st_uid;
	short		st_gid;
	_dev_t		st_rdev;
	__int64		st_size;
	__time32_t	st_atime;
	__time32_t	st_mtime;
	__time32_t	st_ctime;
};
struct _stat64i32 {
	_dev_t		st_dev;
	_ino_t		st_ino;
	_mode_t		st_mode;
	short		st_nlink;
	short		st_uid;
	short		st_gid;
	_dev_t		st_rdev;
	__int32		st_size;
	__time64_t	st_atime;
	__time64_t	st_mtime;
	__time64_t	st_ctime;
};
#endif /* __MSVCRT_VERSION__ >= 0x0800 */
#endif /* __MSVCRT__ */
#define _STAT_DEFINED
#endif /* _STAT_DEFINED */

#ifdef	__cplusplus
extern "C" {
#endif

#if __MSVCRT_VERSION__ < 0x0800
_CRTIMP int __cdecl __MINGW	_fstat (int, struct _stat*);
#endif
_CRTIMP int __cdecl __MINGW	_chmod (const char*, int);
#if __MSVCRT_VERSION__ < 0x0800
_CRTIMP int __cdecl __MINGW	_stat (const char*, struct _stat*);
#endif

#ifndef	_NO_OLDNAMES
/* FIXME for __MSVCRT_VERSION__ >= 0x0800 */
/* These functions live in liboldnames.a. */
_CRTIMP int __cdecl __MINGW	fstat (int, struct stat*);
_CRTIMP int __cdecl __MINGW	chmod (const char*, int);
_CRTIMP int __cdecl __MINGW	stat (const char*, struct stat*);

#endif	/* Not _NO_OLDNAMES */

#if defined (__MSVCRT__)
#if __MSVCRT_VERSION__ < 0x0800
_CRTIMP int __cdecl __MINGW  _fstati64(int, struct _stati64 *);
_CRTIMP int __cdecl __MINGW  _stati64(const char *, struct _stati64 *);
#endif
/* These require newer versions of msvcrt.dll (6.10 or higher).  */ 
#if __MSVCRT_VERSION__ >= 0x0601
_CRTIMP int __cdecl __MINGW _fstat64 (int, struct __stat64*);
_CRTIMP int __cdecl __MINGW _stat64 (const char*, struct __stat64*);
#endif /* __MSVCRT_VERSION__ >= 0x0601 */
#if __MSVCRT_VERSION__ >= 0x0800
_CRTIMP int __cdecl __MINGW	_fstat32 (int, struct __stat32*);
_CRTIMP int __cdecl __MINGW	_stat32 (const char*, struct __stat32*);
_CRTIMP int __cdecl __MINGW	_fstat32i64 (int, struct _stat32i64*);
_CRTIMP int __cdecl __MINGW	_fstat64i32 (int, struct _stat64i32*);
_CRTIMP int __cdecl __MINGW	_stat32i64 (const char*, struct _stat32i64*);
_CRTIMP int __cdecl __MINGW	_stat64i32 (const char*, struct _stat64i32*);
#ifndef _USE_32BIT_TIME_T
_CRTALIAS int __cdecl __MINGW	_fstat (int _v1, struct _stat* _v2)		 { return(_fstat64i32 (_v1,(struct _stat64i32*)_v2)); }
_CRTALIAS int __cdecl __MINGW	_stat (const char* _v1, struct _stat* _v2)	 { return(_stat64i32  (_v1,(struct _stat64i32*)_v2)); }
_CRTALIAS int __cdecl __MINGW	_fstati64 (int _v1, struct _stati64* _v2)	 { return(_fstat64 (_v1,(struct __stat64*)_v2)); }
_CRTALIAS int __cdecl __MINGW	_stati64 (const char* _v1, struct _stati64* _v2) { return(_stat64  (_v1,(struct __stat64*)_v2)); }
#else
_CRTALIAS int __cdecl __MINGW	_fstat (int _v1, struct _stat* _v2)		 { return(_fstat32 (_v1,(struct __stat32*)_v2)); }
_CRTALIAS int __cdecl __MINGW	_stat (const char* _v1, struct _stat* _v2)	 { return(_stat32  (_v1,(struct __stat32*)_v2)); }
_CRTALIAS int __cdecl __MINGW	_fstati64 (int _v1, struct _stati64* _v2)	 { return(_fstat32i64 (_v1,(struct _stat32i64*)_v2)); }
_CRTALIAS int __cdecl __MINGW	_stati64 (const char* _v1, struct _stati64* _v2) { return(_stat32i64  (_v1,(struct _stat32i64*)_v2)); }
#endif /* !_USE_32BIT_TIME_T */
#endif /* __MSVCRT_VERSION__ >= 0x0800 */


#if !defined ( _WSTAT_DEFINED) /* also declared in wchar.h */
#if __MSVCRT_VERSION__ < 0x0800
_CRTIMP int __cdecl __MINGW	_wstat(const wchar_t*, struct _stat*);
_CRTIMP int __cdecl __MINGW	_wstati64 (const wchar_t*, struct _stati64*);
#endif
#if __MSVCRT_VERSION__ >= 0x0601
_CRTIMP int __cdecl __MINGW _wstat64 (const wchar_t*, struct __stat64*);
#endif /* __MSVCRT_VERSION__ >= 0x0601 */
#if __MSVCRT_VERSION__ >= 0x0800
_CRTIMP int __cdecl __MINGW	_wstat32 (const wchar_t*, struct __stat32*);
_CRTIMP int __cdecl __MINGW	_wstat32i64 (const wchar_t*, struct _stat32i64*);
_CRTIMP int __cdecl __MINGW	_wstat64i32 (const wchar_t*, struct _stat64i32*);
#ifndef _USE_32BIT_TIME_T
_CRTALIAS int __cdecl __MINGW	_wstat (const wchar_t* _v1, struct _stat* _v2)	     { return(_wstat64i32 (_v1,(struct _stat64i32*)_v2)); }
_CRTALIAS int __cdecl __MINGW	_wstati64 (const wchar_t* _v1, struct _stati64* _v2) { return(_wstat64 (_v1,(struct __stat64*)_v2)); }
#else
_CRTALIAS int __cdecl __MINGW	_wstat (const wchar_t* _v1, struct _stat* _v2)	     { return(_wstat32 (_v1,(struct __stat32*)_v2)); }
_CRTALIAS int __cdecl __MINGW	_wstati64 (const wchar_t* _v1, struct _stati64* _v2) { return(_wstat32i64 (_v1,(struct _stat32i64*)_v2)); }
#endif /* !_USE_32BIT_TIME_T */
#endif /* __MSVCRT_VERSION__ >= 0x0800 */
#define _WSTAT_DEFINED
#endif /* _WSTAT_DEFIND */
#endif /* __MSVCRT__ */

#ifdef	__cplusplus
}
#endif

#endif	/* Not RC_INVOKED */

#endif	/* Not _STAT_H_ */
