package com.julianotalora.posts.api.network;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Bonestack on 25/07/2018.
 */

@Target(TYPE)
@Retention(RUNTIME)
public @interface AutoGson {
}

