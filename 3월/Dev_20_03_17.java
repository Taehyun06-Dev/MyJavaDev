import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Dev_20_03_17 {

    /*주제: 여러가지 암호화와 복호화
    암호화는 암호 키(특정의 비트열)를 사용하여 정보를 암호문으로 변환하는 것이고, 복호화는 복호 키를 사용하여 원래의 정보를 복원하는 것을 말한다.
    해쉬함수(Hash Function)는 임의의 길이를 갖는 메시지를 입력하여 고정된 길이의 해쉬값을 출력하는 함수이다.
    널리 사용되는 AES-256, RSA 암호화 및 복호화(다음번에 하나의 프로젝트로), 해쉬(SHA-256)에 대해 탐구해본다.
    */

    private String Encrypt_Value  = "Apple";

    //메인 실행 함수
    public static void main(String[] args){
        new Dev_20_03_17().Encrypt_AES();
        new Dev_20_03_17().Encrypt_Hash();
    }

    //AES-256 암호화 및 복호화 -> 특정 키로 암호화 및 복호화를 진행
    public void Encrypt_AES(){
        try{
            String Encrypt_Pass = "12345678901234567890123456789012";
            String Encrypt_Iv = "1234567890123456";

            SecretKey clsKey = new SecretKeySpec( Encrypt_Pass.getBytes(), "AES" );
            IvParameterSpec clsIV = new IvParameterSpec( Encrypt_Iv.getBytes() );
            Cipher clsCipher = Cipher.getInstance( "AES/CBC/PKCS5Padding" );

            //암호화
            clsCipher.init( Cipher.ENCRYPT_MODE, clsKey, clsIV );
            byte [] arrEnc = clsCipher.doFinal( Encrypt_Value.getBytes(StandardCharsets.UTF_8) );
            System.out.println( "(AES) 암호화값: [" + new String( arrEnc, StandardCharsets.UTF_8) + "]" );

            //복호화
            clsCipher.init( Cipher.DECRYPT_MODE, clsKey, clsIV );
            byte [] arrDec = clsCipher.doFinal( arrEnc );
            System.out.println( "(AES) 복호화값: [" + new String( arrDec, StandardCharsets.UTF_8) + "]" );

            //Exception으로 통일
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Hash(SHA-245) 암호화 -> 특정 키로 암호화, 복호화를 지원하지 않는다. 주로 비밀번호 저장에 많이 사용됨.
    public void Encrypt_Hash(){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(Encrypt_Value.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            System.out.println("(HASH) 암호화값: ["+sb.toString()+"]");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
