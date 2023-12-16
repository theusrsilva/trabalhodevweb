package com.trabalhodevweb.crm.model;

public enum Cargo {
        ADMINISTRADOR("administrador"),
        ORGANIZADOR("organizador"),
        USUARIO("usuario");

        private String cargo;

        Cargo(String cargo){
            this.cargo = cargo;
        }

        public String getCargo(){
            return cargo;
        }


    public static boolean contains(String value) {
        for (Cargo cargo : Cargo.values()) {
            if (cargo.getCargo().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }


}
