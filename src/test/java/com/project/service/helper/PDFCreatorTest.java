package com.project.service.helper;

import com.project.MockData;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;

import static com.project.MockData.*;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PDFCreatorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private PDFCreator pdfCreator = new PDFCreator();

    @Test
    public void createFontsShouldReturnFonts() {
        final String filePath = pdfCreator.createPDF(1L, singletonList(MOCK_TICKET));

        File file = new File("tickets/" + filePath);
        final boolean isDeleted = file.delete();

        assertThat(filePath, is(notNullValue()));
        assertThat(isDeleted, is(true));
    }
}