package com.studentsinternship.demo.utils.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority(T(com.studentsinternship.demo.entity.enums.Role).RECRUITER)")
@Target(ElementType.METHOD)
public @interface AllowRecruiter {
}
