class UTF8Validation {

    // String Manipulation
    public boolean validUtf8(int[] data) {

        // Number of bytes in the current UTF-8 character
        int numberOfBytesToProcess = 0;

        // For each integer in the data array.
        for (int i = 0; i < data.length; i++) {

            // Get the binary representation. We only need the least significant 8 bits
            // for any given number.
            String binRep = Integer.toBinaryString(data[i]);
            binRep = binRep.length() >= 8 ? binRep.substring(binRep.length() - 8)
                    : "00000000".substring(binRep.length() % 8) + binRep;

            // If this is the case then we are to start processing a new UTF-8 character.
            if (numberOfBytesToProcess == 0) {

                // Get the number of 1s in the beginning of the string.
                for (int j = 0; j < binRep.length(); j++) {
                    if (binRep.charAt(j) == '0') {
                        break;
                    }

                    numberOfBytesToProcess += 1;
                }

                // 1 byte characters
                if (numberOfBytesToProcess == 0) {
                    continue;
                }

                // Invalid scenarios according to the rules of the problem.
                if (numberOfBytesToProcess > 4 || numberOfBytesToProcess == 1) {
                    return false;
                }

            } else {

                // Else, we are processing integers which represent bytes which are a part of
                // a UTF-8 character. So, they must adhere to the pattern `10xxxxxx`.
                if (!(binRep.charAt(0) == '1' && binRep.charAt(1) == '0')) {
                    return false;
                }
            }

            // We reduce the number of bytes to process by 1 after each integer.
            numberOfBytesToProcess -= 1;
        }

        // This is for the case where we might not have the complete data for
        // a particular UTF-8 character.
        return numberOfBytesToProcess == 0;
    }

    // Bit Manipulation
    public boolean validUtf8I(int[] data) {

        // Number of bytes in the current UTF-8 character
        int numberOfBytesToProcess = 0;

        // Masks to check two most significant bits in a byte.
        int mask1 = 1 << 7;
        int mask2 = 1 << 6;

        // For each integer in the data array.
        for (int i = 0; i < data.length; i++) {
            // If this is the case then we are to start processing a new UTF-8 character.
            if (numberOfBytesToProcess == 0) {
                int mask = 1 << 7;
                while ((mask & data[i]) != 0) {
                    numberOfBytesToProcess += 1;
                    mask = mask >> 1;
                }

                // 1 byte characters
                if (numberOfBytesToProcess == 0) {
                    continue;
                }

                // Invalid scenarios according to the rules of the problem.
                if (numberOfBytesToProcess > 4 || numberOfBytesToProcess == 1) {
                    return false;
                }

            } else {

                // data[i] should have most significant bit set and
                // second most significant bit unset. So, we use the two masks
                // to make sure this is the case.
                if (!((data[i] & mask1) != 0 && (mask2 & data[i]) == 0)) {
                    return false;
                }
            }

            // We reduce the number of bytes to process by 1 after each integer.
            numberOfBytesToProcess -= 1;
        }

        // This is for the case where we might not have the complete data for
        // a particular UTF-8 character.
        return numberOfBytesToProcess == 0;
    }

    // Trick
    public boolean validUtf8II(int[] data) {
        int i = 0;

        while (i < data.length) {
            int len = getLen(data[i]);
            if (len == -1) {
                return false;
            }
            int j = i + 1;
            while (j <= i + len - 1) {
                if (j >= data.length) {
                    return false;
                }
                if (data[j] >> 6 != 2) {
                    return false;
                }
                j++;
            }
            i = j;
        }
        return true;
    }

    private int getLen(int d) {
        if (d >> 3 == 30) {
            return 4;
        }
        if (d >> 4 == 14) {
            return 3;
        }
        if (d >> 5 == 6) {
            return 2;
        }
        if (d >> 7 == 0) {
            return 1;
        }
        return -1;
    }
}