package com.ardctraining.facades.feedback;

import com.ardctraining.facades.feedback.data.CustomerFeedbackData;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;

import java.util.List;

public interface CustomerFeedbackFacade extends CustomerFacade {

    List<CustomerFeedbackData> findByStatus();

    void save(String subject, String message);
}
