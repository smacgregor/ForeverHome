package com.smacgregor.foreverhome.test.common.injection.component;

import com.smacgregor.foreverhome.injection.component.ApplicationComponent;
import com.smacgregor.foreverhome.test.common.injection.module.ApplicationTestModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
