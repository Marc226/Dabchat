package com.example.main.di.Scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@MainActivityScope
@Retention(RetentionPolicy.RUNTIME)
public @interface DownloadScope {
}
