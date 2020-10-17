package tech.jaykay12;

import org.springframework.stereotype.Component;

@Component
public class ResponseDTO <T>{
    private T cleanedString;
    private T measurementUnit;

    public T getMeasurementUnit() { return measurementUnit; }

    public void setMeasurementUnit(T measurementUnit) { this.measurementUnit = measurementUnit; }

    public T getCleanedString() { return cleanedString; }

    public void setCleanedString(T cleanedString) { this.cleanedString = cleanedString; }

}
