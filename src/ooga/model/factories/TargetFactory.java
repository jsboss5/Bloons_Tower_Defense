package ooga.model.factories;

import ooga.PropertiesReader;
import ooga.model.GamePiece;
import ooga.model.targets.Target;

import java.util.HashSet;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

public class TargetFactory implements Factory{
    private Set<String> healthKeys;
    private Set<String> targetTypes;
    private Properties properties;
    private final int defaultX = 0;
    private final int defaultY = 0;
    private PropertiesReader pReader;
    private Properties textProperties;

    @Override
    public GamePiece getGamePiece(Properties propertyFile) {
        pReader = new PropertiesReader();
        textProperties = (Properties)pReader.read("text/FactoryText.properties");
        healthKeys = new HashSet<>();
        targetTypes = new HashSet<>();
        properties = propertyFile;
        Random random = new Random();

        updateKeySet(healthKeys, (String)textProperties.get("healthKey"));
        updateKeySet(targetTypes, (String)textProperties.get("targetKey"));

        double randDouble = random.nextDouble();

        int targetHealth = generateTargetHealth(randDouble);
        String targetType = generateTargetType(randDouble);


        String className = (String)textProperties.get("targetClass") + targetType;

        try{
            Object newObjectTarget = Class.forName(className).getConstructors()[0].newInstance(defaultX, defaultY, 1, targetHealth, 1,
                    targetType);
            Target newTarget = (Target)newObjectTarget;
            return newTarget;
        }
        catch(Exception e){
        }
        return null;
    }

    private String generateTargetType(double randDouble) {
        double sumType = 0.0;
        String targetType = "";
        for (String type : targetTypes) {
            sumType += Double.parseDouble((String) properties.get(type));
            if (randDouble < sumType) {
                int indexOfType = type.indexOf((String)textProperties.get("ratio"));
                targetType = type.substring(0, indexOfType);
                break;
            }
        }
        return targetType;
    }

    private int generateTargetHealth(double randDouble) {
        double sumHealth = 0.0;
        int targetHealth = 0;
        for (String health : healthKeys) {
            sumHealth += Double.parseDouble((String) properties.get(health));
            if (randDouble < sumHealth) {
                targetHealth = Integer.parseInt(health.replaceAll("[\\D]", ""));
                break;
            }
        }
        return targetHealth;
    }

    private void updateKeySet(Set<String> keySet, String keyType) {
        for (Object key : properties.keySet()) {
            if (key.toString().contains(keyType)) {
                keySet.add((String) key);
            }
        }
    }
}
