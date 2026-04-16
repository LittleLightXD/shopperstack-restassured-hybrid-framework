package api.payload;

/**
 * CategoryManagementPayload - Request payload for Category Management operations
 */
public class CategoryManagementPayload {
    private String categoryId;
    private String categoryName;
    private String subcategoryName;
    private String parentCategoryId;
    private String description;
    private boolean isActive;

    // Constructors
    public CategoryManagementPayload() {}

    public CategoryManagementPayload(String categoryName, String description, boolean isActive) {
        this.categoryName = categoryName;
        this.description = description;
        this.isActive = isActive;
    }

    // Getters and Setters
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }
}
