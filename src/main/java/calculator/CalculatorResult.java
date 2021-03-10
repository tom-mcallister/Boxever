package calculator;

import java.util.List;
import java.util.stream.Collectors;

public interface CalculatorResult {
    int getDuration();

    List<String> getPath();

    List<Integer> getDurations();

    default String prettyString() {

        StringBuilder stringBuilder = new StringBuilder();
        List<String> path = getPath();
        List<Integer> durations = getDurations();

        for(int i = 0; i < path.size() -1; i++) {
            String source = path.get(i);
            String destination = path.get(i+1);
            stringBuilder.append(source);
            stringBuilder.append(" -- ");
            stringBuilder.append(destination);
            stringBuilder.append(" (");
            stringBuilder.append(durations.get(i+1) - durations.get(i));
            stringBuilder.append(")\n");

        }
        stringBuilder.append("time: ");
        stringBuilder.append(getDuration());

        return stringBuilder.toString();
    }

}
