package me.emmarivera.weather.internal

import javax.inject.Scope

/**
 * A Dagger2 scope which is used for application wide objects. This is essentially a singleton.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(allowedTargets = [AnnotationTarget.CLASS, AnnotationTarget.FUNCTION])
annotation class AppScope

/**
 * Scope of dependencies that should live the entirety of the service.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(allowedTargets = [AnnotationTarget.CLASS, AnnotationTarget.FUNCTION])
annotation class ServiceScope

/**
 * Scope of dependencies that should live the entirety of the activity. Objects which are dependent
 * on the context, should always live this scope, or below it in the dependency graph.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(allowedTargets = [AnnotationTarget.CLASS, AnnotationTarget.FUNCTION])
annotation class ActivityScope

/**
 * The FragmentScoped custom scoping annotation specifies that the lifespan of a dependency be
 * the same as that of a Fragment. This is used to annotate dependencies that behave like a
 * singleton within the lifespan of a Fragment
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(allowedTargets = [AnnotationTarget.CLASS, AnnotationTarget.FUNCTION])
annotation class FragmentScope

/**
 * The [SubfragmentScope] custom scoping annotation specifies that the lifespan of a dependency be
 * the same as that of a child fragment. This is used to annotate dependencies that behave like a
 * singleton within the lifespan of a Fragment
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(allowedTargets = [AnnotationTarget.CLASS, AnnotationTarget.FUNCTION])
annotation class SubfragmentScope
