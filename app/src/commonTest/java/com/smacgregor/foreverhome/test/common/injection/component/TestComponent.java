package com.smacgregor.foreverhome.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import com.smacgregor.foreverhome.injection.component.ApplicationComponent;
import com.smacgregor.foreverhome.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
