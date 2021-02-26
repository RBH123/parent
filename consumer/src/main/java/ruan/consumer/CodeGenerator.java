package ruan.consumer;

import ruan.common.util.CodeGenerate;

public class CodeGenerator {

    public static void main(String[] args) {
        String javaPath = System.getProperty("user.dir").concat("\\consumer");
        String projectPath = "\\ruan\\consumer";
        String[] tables = new String[]{"users"};
        CodeGenerate.generate(javaPath,projectPath,tables);
    }
}
