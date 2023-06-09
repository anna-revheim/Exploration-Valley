package no.uib.inf101;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import no.uib.inf101.sem2.ExploartionValley.view.GameView;
import no.uib.inf101.sem2.ExploartionValley.view.UI;

public class UITest {

    private UI ui;
    private GameView mockView;
    public BufferedImage mockImage;

    @Before
    public void setUp() throws Exception {
        mockView = mock(GameView.class);
        ui = new UI(mockView);
        mockImage = Mockito.mock(BufferedImage.class);
    }

    @Test
    public void testGetHPimage() {
        assertEquals(ui.hp0, ui.getHPimage(0));
        assertEquals(ui.hp2, ui.getHPimage(1));
        assertEquals(ui.hp4, ui.getHPimage(2));
        assertEquals(ui.hp6, ui.getHPimage(3));
        assertEquals(ui.hp8, ui.getHPimage(4));

        assertEquals(null, ui.getHPimage(-1));
        assertEquals(null, ui.getHPimage(5));
    }
}
