package com.ardctraining.facades.feedback.impl;

import com.ardctraining.core.feedback.service.CustomerFeedbackService;
import com.ardctraining.core.model.CustomerFeedbackModel;
import com.ardctraining.facades.feedback.CustomerFeedbackFacade;
import com.ardctraining.facades.feedback.data.CustomerFeedbackData;
import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;

public class DefaultCustomerFeedbackFacade extends DefaultCustomerFacade implements CustomerFeedbackFacade {

    private CustomerFeedbackService customerFeedbackService;
    private Converter<CustomerFeedbackModel, CustomerFeedbackData> customerFeedbackConverter;

    @Override
    public List<CustomerFeedbackData> findByStatus() {
        final CustomerModel customerModel = (CustomerModel) getUserService().getCurrentUser();
        return customerFeedbackConverter.convertAll(customerFeedbackService.findByStatus(customerModel));
    }

    @Override
    public void save(String subject, String message) {
        final CustomerModel customerModel = (CustomerModel) getUserService().getCurrentUser();

        CustomerFeedbackModel customerFeedbackModel = new CustomerFeedbackModel();
        customerFeedbackModel.setMessage(message);
        customerFeedbackModel.setSubject(subject);
        customerFeedbackModel.setCustomer(customerModel);
        getCustomerFeedbackService().save(customerFeedbackModel);
    }

    public CustomerFeedbackService getCustomerFeedbackService() {
        return customerFeedbackService;
    }

    public void setCustomerFeedbackService(CustomerFeedbackService customerFeedbackService) {
        this.customerFeedbackService = customerFeedbackService;
    }

    public Converter<CustomerFeedbackModel, CustomerFeedbackData> getCustomerFeedbackConverter() {
        return customerFeedbackConverter;
    }

    public void setCustomerFeedbackConverter(Converter<CustomerFeedbackModel, CustomerFeedbackData> customerFeedbackConverter) {
        this.customerFeedbackConverter = customerFeedbackConverter;
    }

}
