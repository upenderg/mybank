package com.bank;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

@RestController
public class MoneyController {

    //There are 3 payout boxes: 1 for notes, 1 for coins > 20mm and 1 for coins <= 20mm
    //Notes available 1000, 500, 200, 100, 50
    //5 coins are available: 20 (40mm), 10 (20mm), 5 (50mm), 2 (30mm) and 1 (10mm)

    /***
     * Provides a way to get the denomination of the Amount to be Withdrawn
     * @param amountToBeWithdrawn - Amount to be withdrawn
     * @return - Returns a Map of with content
     */
    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public Map<String, Object> withDraw(@RequestBody long amountToBeWithdrawn) {
        checkNotNull(amountToBeWithdrawn);
        String response = "Amount withdrawn value should be above 0";
        final Map<String, Object> model = new HashMap<>();
        if (amountToBeWithdrawn > 0) {
            final String outPut = MoneyControllerHelper.formatOutPut(MoneyControllerHelper.getDenomination(amountToBeWithdrawn));
            response = MessageFormat.format("User wants to withdraw {0}. The ATM responds with: {1} ", amountToBeWithdrawn,outPut);
            System.out.println(response);
        }
        model.put("content", response);
        return model;

    }
}
