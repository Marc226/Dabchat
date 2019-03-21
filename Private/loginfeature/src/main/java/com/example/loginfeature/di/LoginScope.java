package com.example.loginfeature.di;

import com.example.common.Common.di.Scopes.MainActivityScope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@MainActivityScope
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginScope {
}
