package com.ardctraining.core.handler;

import com.ardctraining.core.enums.CustomerFeedbackEnum;
import com.ardctraining.core.model.CustomerFeedbackModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.servicelayer.time.TimeService;
import org.joda.time.DateTime;

import javax.annotation.Resource;

public class CustomerFeedbackStatusHandler implements DynamicAttributeHandler<CustomerFeedbackEnum, CustomerFeedbackModel> {

    private ConfigurationService configurationService;
    private TimeService timeService;

    @Override
    public CustomerFeedbackEnum get(CustomerFeedbackModel model) {
        int days = getConfigurationService().getConfiguration().getInt("feedback.status.days.calculation");
        DateTime today = new DateTime();
        DateTime submittedDate = new DateTime(model.getSubmittedDate());
        DateTime limitDate = submittedDate.plusDays(days);

        if (today.isBefore(limitDate)){
            return CustomerFeedbackEnum.READ;
        }else if(today.isAfter(limitDate)){
            return CustomerFeedbackEnum.READ_PASTDUE;
        }else if(today.isAfter(limitDate) && !model.getRead()){
            return CustomerFeedbackEnum.PASTDUE;
        }else {
            return CustomerFeedbackEnum.NOT_READ;
        }
    }

    private ConfigurationService getConfigurationService() {
        return configurationService;
    }

    @Override
    public void set(CustomerFeedbackModel model, CustomerFeedbackEnum customerFeedbackEnum) {
        throw new UnsupportedOperationException("Write is not a valid operation for this dynamic attribute");
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public TimeService getTimeService() {
        return timeService;
    }

    public void setTimeService(TimeService timeService) {
        this.timeService = timeService;
    }
}
