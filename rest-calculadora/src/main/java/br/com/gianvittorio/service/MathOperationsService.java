package br.com.gianvittorio.service;

import br.com.gianvittorio.exception.MyUnsupportedMathOperationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class MathOperationsService {
    public Double sum(String numberOne, String numberTwo) throws Exception {
        if (numberOne == null || numberTwo == null) {
            return 0D;
        }

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new MyUnsupportedMathOperationException("Please, provide a numeric value!");
        }

        return Double.parseDouble(numberOne) + Double.parseDouble(numberTwo);
    }

    public Double subtract(String numberOne,
                           String numberTwo) {
        if (numberOne == null || numberTwo == null) {
            return 0D;
        }

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new MyUnsupportedMathOperationException("Please, provide a numeric value!");
        }

        return Double.parseDouble(numberOne) - Double.parseDouble(numberTwo);
    }

    public Double multiply(String numberOne,
                           String numberTwo) {
        if (numberOne == null || numberTwo == null) {
            return 0D;
        }

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new MyUnsupportedMathOperationException("Please, provide a numeric value!");
        }

        return Double.parseDouble(numberOne) * Double.parseDouble(numberTwo);
    }

    public Double divide(String numberOne,
                         String numberTwo) {
        if (numberOne == null || numberTwo == null) {
            return 0D;
        }

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new MyUnsupportedMathOperationException("Please, provide a numeric value!");
        }

        return Double.parseDouble(numberOne) / Double.parseDouble(numberTwo);
    }

    public Double sqrt(String number) {
        if (number == null) {
            return 0D;
        }

        if (!isNumeric(number)) {
            throw new MyUnsupportedMathOperationException("Please, provide a numeric value!");
        }

        return (double) Math.sqrt(Double.parseDouble(number));
    }

    public Double avg(String numberOne,
                      String numberTwo) throws Exception {
        return sum(numberOne, numberTwo) / 2.;
    }

    private boolean isNumeric(String number) {
        if (number == null || number.isEmpty()) {
            return false;
        }

        return number.matches("^[-+]?(\\d\\.)?\\d+$");
    }
}
