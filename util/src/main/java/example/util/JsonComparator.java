package example.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.comparator.DefaultComparator;

class JsonComparator extends DefaultComparator {
    private List<Pattern> ignoreFieldRegexPathList = null;

    JsonComparator(JSONCompareMode mode, String[] ignoreFieldRegexPath) {
        super(mode);
        if (ignoreFieldRegexPath!=null) {
            this.ignoreFieldRegexPathList = Arrays.stream(ignoreFieldRegexPath)
                .map(Pattern::compile)
                .collect(Collectors.toList());
        }
    }

    @Override
    public void compareValues(
        final String prefix,
        final Object expectedValue,
        final Object actualValue,
        final JSONCompareResult result
    ) throws JSONException {
        if (isIgnore(prefix)) {
            return;
        }
        super.compareValues(prefix, expectedValue, actualValue, result);
    }

    private boolean isIgnore(final String path) {
        if (ignoreFieldRegexPathList==null) {
            return false;
        }
        for (Pattern pattern : ignoreFieldRegexPathList) {
            if (pattern.matcher(path).matches()) {
                return true;
            }
        }
        return false;
    }
}
