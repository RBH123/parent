package ruan.consumer;

import ruan.common.util.CodeGenerate;

public class CodeGenerator {

    public static void main(String[] args) {
        String javaPath = System.getProperty("user.dir").concat("\\consumer");
        String projectPath = "\\ruan\\consumer";
        String[] tables = new String[]{"message_record"};
        CodeGenerate.generate(javaPath,projectPath,tables);
    }
}
