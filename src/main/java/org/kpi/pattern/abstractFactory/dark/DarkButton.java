package org.kpi.pattern.abstractFactory.dark;

import org.kpi.pattern.abstractFactory.WebButton;

public class DarkButton implements WebButton {
    @Override
    public String renderHtml() {
        return "<button type='submit' style='background-color: #333; color: white; border: 1px solid #555; padding: 5px 10px; cursor: pointer;'>Execute</button>";
    }
}