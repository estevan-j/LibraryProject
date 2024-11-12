package com.LibraryProject.LibraryProject.DTO;

public record BookConsultDTO(
        String title,
        String authors,
        String languages,
        Integer download_count
) {

    @Override
    public String toString() {
        return "+++++++++++++++++++++++++++++++++++++++++++++++++++\n" +
                "\t\t" + title  +
                "\nDonwloads: " + download_count +
                "\nLanguages: " + languages +
                "\nAuthors: " + authors+
                "\nNumero de Descargas: " + download_count+
                "\n+++++++++++++++++++++++++++++++++++++++++++++++++++";
    }
}
