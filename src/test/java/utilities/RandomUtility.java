package utilities;

// import java.security.SecureRandom;
import java.text.SimpleDateFormat;
// import java.time.LocalDateTime;
// import java.time.ZoneId;
// import java.time.format.TextStyle;
import java.util.*;

// import org.apache.velocity.app.event.ReferenceInsertionEventHandler.referenceInsertExecutor;

import com.github.javafaker.Faker;

public class RandomUtility {

    private static final Faker faker = new Faker();
    private static final Random random = new Random();
    private static final String[] months = new String[]{"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

//     public String generateRandomString() {
//      int length = 5;   
//     String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
//     SecureRandom random = new SecureRandom();
//     StringBuilder sb = new StringBuilder(length);

//     for (int i = 0; i < length; i++) {
//         int index = random.nextInt(alphabet.length());
//         sb.append(alphabet.charAt(index));
//     }

//     return sb.toString();
// }
    public String generateRandomString() {
        int length = 5;
        return faker.lorem().characters(length);
    }


    public String generateRandomNumberss() {
        int digit = 7;
        String number = faker.number().digits(digit);;
        return number;
    }
    
    public int generateRandomNumber(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public String generateRandomTime() {
        int hour = generateRandomNumber(0, 12);
        int minute = generateRandomNumber(0, 59);
        String ampm = random.nextBoolean() ? "AM" : "PM";

        return String.format("%02d:%02d %s", hour, minute, ampm);
    }

    public String[] generateRandomTimeRange() {
        int startHour = generateRandomNumber(0, 12);
        int startMinute = generateRandomNumber(0, 59);
        String startAmpm = random.nextBoolean() ? "AM" : "PM";

        String startTime = String.format("%02d:%02d %s", startHour, startMinute, startAmpm);
        int duration = generateRandomNumber(15, 180);

        int startTotalMinutes = (startHour % 12) * 60 + startMinute + (startAmpm.equals("PM") ? 720 : 0);
        int endTotalMinutes = startTotalMinutes + duration;

        int endHour = (endTotalMinutes % 720) / 60;
        int endMinute = endTotalMinutes % 60;
        String endAmpm = endTotalMinutes >= 720 ? "PM" : "AM";

        String endTime = String.format("%02d:%02d %s", endHour, endMinute, endAmpm);
        return new String[]{startTime, endTime};
    }

    public String generateMultipleLineContent() {
        int wordCount = 5;
        String sentence = faker.lorem().sentence(wordCount);
        return sentence;
    }

    public String generateRandomEmail() {
        return "user" + generateRandomNumber(1000, 9999) + "@example.com";
    }

    public List<String> getRandomSelectedValuesFromArray(List<String> array) {
        List<String> selected = new ArrayList<>();
        int count = array.size() == 1 ? 1 : Math.min(5, generateRandomNumber(1, array.size() - 1));

        Collections.shuffle(array);
        for (int i = 0; i < count; i++) {
            selected.add(array.remove(0));
        }

        return selected;
    }

    public String getRandomSelectedOneValueFromArray(List<String> array) {
        return array.get(random.nextInt(array.size()));
    }

    public String generateFakePhoneNumber() {
        String[] firstDigits = {"9", "8", "7", "6"};
        StringBuilder number = new StringBuilder("+91");
        number.append(firstDigits[random.nextInt(firstDigits.length)]);

        for (int i = 0; i < 9; i++) {
            number.append(random.nextInt(10));
        }

        return number.toString();
    }

    public String formatDateToDDMMYYYY(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public Object[] getRandomDate() {
        long currentTime = System.currentTimeMillis();
        long twoYearsMillis = 2L * 365 * 24 * 60 * 60 * 1000;
        long randomOffset = (long) (Math.random() * twoYearsMillis * 2) - twoYearsMillis;
        Date randomDate = new Date(currentTime + randomOffset);

        Calendar cal = Calendar.getInstance();
        cal.setTime(randomDate);

        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        String month = months[cal.get(Calendar.MONTH)];
        String year = String.valueOf(cal.get(Calendar.YEAR));

        String formatted = formatDateToDDMMYYYY(randomDate);
        return new Object[]{new String[]{day, month, year}, formatted};
    }

    public Object[] getRandomDateRange() {
        long now = System.currentTimeMillis();
        long twoYearsMillis = 2L * 365 * 24 * 60 * 60 * 1000;

        long startMillis = now - twoYearsMillis + (long) (Math.random() * (2 * twoYearsMillis));
        long endMillis = startMillis + (long) (Math.random() * (2 * twoYearsMillis - (startMillis - now + twoYearsMillis)));

        Date startDate = new Date(startMillis);
        Date endDate = new Date(endMillis);

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        String[] startArray = {
                String.valueOf(startCal.get(Calendar.DAY_OF_MONTH)),
                months[startCal.get(Calendar.MONTH)],
                String.valueOf(startCal.get(Calendar.YEAR))
        };
        String[] endArray = {
                String.valueOf(endCal.get(Calendar.DAY_OF_MONTH)),
                months[endCal.get(Calendar.MONTH)],
                String.valueOf(endCal.get(Calendar.YEAR))
        };

        String startFormatted = formatDateToDDMMYYYY(startDate);
        String endFormatted = formatDateToDDMMYYYY(endDate);

        return new Object[]{startArray, startFormatted, endArray, endFormatted};
    }

    public String getRandomLatLong() {
        double latitude = -90 + (90 + 90) * random.nextDouble();
        double longitude = -180 + (180 + 180) * random.nextDouble();
        String latLong = String.format("%.6f", latitude) + "," +String.format("%.6f", longitude);
        return latLong;
    }

    public String generateRandomText(int length) {
        String specialChars = "!@#$%^&*()_+[]{}|;:,.<>?";
        String numbers = "0123456789";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String allChars = specialChars + numbers + lower + upper;

        StringBuilder password = new StringBuilder();
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));
        password.append(numbers.charAt(random.nextInt(numbers.length())));
        password.append(lower.charAt(random.nextInt(lower.length())));
        password.append(upper.charAt(random.nextInt(upper.length())));

        for (int i = password.length(); i < length; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        List<Character> chars = new ArrayList<>();
        for (char c : password.toString().toCharArray()) chars.add(c);
        Collections.shuffle(chars);

        StringBuilder shuffled = new StringBuilder();
        for (char c : chars) shuffled.append(c);
        return shuffled.toString();
    }
}