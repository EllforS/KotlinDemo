package com.ellfors.kotlindemo.di

import javax.inject.Scope

/**
 * 自定义Activity生命周期
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope
