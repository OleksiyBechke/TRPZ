package org.kpi.pattern.abstractFactory.light;

import org.kpi.pattern.abstractFactory.WebButton;

public class LightButton implements WebButton {
    @Override
    public String renderHtml() {
        return "<button type='submit' style='background-color: #f0f0f0; color: black; border: 1px solid #ccc; padding: 5px 10px; cursor: pointer;'>Run Script</button>";
    }
}