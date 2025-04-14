package banco.pichincha.web.utils;

import java.security.SecureRandom;

public class GenerateCuenta {
    private static final SecureRandom random = new SecureRandom();

    public static String generarCuenta() {
        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}
