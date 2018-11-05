package net.oneqay.struct;

public class StructError extends RuntimeException {

    private static final long serialVersionUID = -939351696853848429L;

    public StructError(final String explanation) {
        super(explanation);
    }
}
