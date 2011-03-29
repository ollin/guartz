/*
 *    Copyright 2009-2011 The 99 Software Foundation
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.nnsoft.guice.guartz;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.quartz.Job;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;

import com.google.inject.Injector;

/**
 * 
 */
@Singleton
final class InjectorJobFactory implements JobFactory {

    @Inject
    private Injector injector;

    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    public Job newJob(TriggerFiredBundle bundle) throws SchedulerException {
        @SuppressWarnings("unchecked")
        Class<? extends Job> jobClass = bundle.getJobDetail().getJobClass();

        return this.injector.getInstance(jobClass);
    }

}
