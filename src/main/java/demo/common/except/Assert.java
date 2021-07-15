package demo.common.except;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 参数校验
 *
 * @author 邱哲奇
 * @date 不晓得
 */
public class Assert {

    public static void isTrue(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void isFalse(boolean expression) {
        if (expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void isNull(Object object) {
        if (object != null) {
            throw new IllegalArgumentException();
        }
    }

    public static void notNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void hasLength(String text) {
        if (!StringUtils.hasLength(text)) {
            throw new IllegalArgumentException();
        }
    }

    public static void hasLength(String ... text) {
        for(String t: text){
            hasLength(t);
        }
    }

    public static void hasText(String text) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException();
        }
    }

    public static void doesNotContain(String textToSearch, String substring) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) &&
                textToSearch.contains(substring)) {
            throw new IllegalArgumentException();
        }
    }

    public static void notEmpty(Object[] array) {
        if (ObjectUtils.isEmpty(array)) {
            throw new IllegalArgumentException();
        }
    }

    public static void notEmpty(Collection<?> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new IllegalArgumentException();
        }
    }

    public static void notEmpty(Map<?, ?> map) {
        if (CollectionUtils.isEmpty(map)) {
            throw new IllegalArgumentException();
        }
    }

    public static void noNullElements(Object[] array) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    public static void noNullElements(Collection<?> collection) {
        if (collection != null) {
            for (Object element : collection) {
                if (element == null) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }
}
