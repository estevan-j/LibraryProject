package com.LibraryProject.LibraryProject.service;

public interface IConverData {
    <T> T getData(String json, Class<T> clazz);
}
