package com.project.backend.utils;

public class MessageKey {
    public static final String LOGIN_SUCCESSFULLY = "user.login.login_successfully";
    public static final String LOGIN_FAILED = "user.login.login_failed";

    public static final String REGISTER_SUCCESSFULLY = "user.register.register_successfully";
    public static final String PASSWORD_NOT_MATCH = "user.register.password_not_match";
    public static final String REGISTER_FAILED = "user.register.register_failed";

    public static final String CREATE_CATEGORY_SUCCESSFULLY = "category.create_category.create_successfully";
    public static final String CREATE_CATEGORY_FAILED = "category.create_category.create_failed";
    public static final String UPDATE_CATEGORY_SUCCESSFULLY = "category.update_category.update_successfully";
    public static final String DELETE_CATEGORY_SUCCESSFULLY = "category.delete_category.delete_successfully";

    public static final String DELETE_ORDER_SUCCESSFULLY = "order.delete_order.delete_successfully";
    public static final String DELETE_ORDER_DETAIL_SUCCESSFULLY = "order.delete_order_detail.delete_successfully";

    public static final String UPDATE_IMAGES_MAX_5 = "product.upload_images.error_max_5_images";
    public static final String UPDATE_IMAGES_FILE_LARGE = "upload_images.file_large";
    public static final String UPDATE_IMAGES_MUST_BE_IMAGE = "file_must_be_image";
}