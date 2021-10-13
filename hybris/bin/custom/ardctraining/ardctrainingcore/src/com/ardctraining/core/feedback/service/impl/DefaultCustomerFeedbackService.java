package com.ardctraining.core.feedback.service.impl;

import com.ardctraining.core.feedback.dao.CustomerFeedbackDao;
import com.ardctraining.core.feedback.service.CustomerFeedbackService;
import com.ardctraining.core.handler.CustomerFeedbackStatusHandler;
import com.ardctraining.core.model.CustomerFeedbackModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.List;

public class DefaultCustomerFeedbackService implements CustomerFeedbackService {

    private CustomerFeedbackDao customerFeedbackDao;
    private ModelService modelService;
    private TimeService timeService;

    public TimeService getTimeService() {
        return timeService;
    }

    public void setTimeService(TimeService timeService) {
        this.timeService = timeService;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public CustomerFeedbackDao getCustomerFeedbackDao() {
        return customerFeedbackDao;
    }

    public void setCustomerFeedbackDao(CustomerFeedbackDao customerFeedbackDao) {
        this.customerFeedbackDao = customerFeedbackDao;
    }

    @Override
    public List<CustomerFeedbackModel> findByStatus(CustomerModel customer) {
        ServicesUtil.validateParameterNotNull(customer, "customer cannot be null");
        return getCustomerFeedbackDao().findByStatus(customer);
    }

    @Override
    public void save(CustomerFeedbackModel customerFeedbackModel) {

        customerFeedbackModel.setRead(Boolean.FALSE);
        customerFeedbackModel.setSubmittedDate(getTimeService().getCurrentTime());
        getModelService().save(customerFeedbackModel);
    }
}
