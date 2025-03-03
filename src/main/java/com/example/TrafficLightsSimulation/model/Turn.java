package com.example.TrafficLightsSimulation.model;

public enum Turn {
    S, // Straight
    R, // Right
    L; // Left

    public static Turn getTurn(Position start, Position end) {

        if ((start == Position.N && end == Position.S) ||
                (start == Position.S && end == Position.N) ||
                (start == Position.E && end == Position.W) ||
                (start == Position.W && end == Position.E)) {
            return S;
        }
        int startIndex = start.toNumber();
        int endIndex = end.toNumber();

        // Jeśli różnica między kierunkiem startowym a końcowym wynosi 1, to pojazd skręca w prawo
        if ((endIndex - startIndex + 4) % 4 == 1) {
            return R;
        }

        // Jeśli różnica między kierunkiem startowym a końcowym wynosi 3, to pojazd skręca w lewo
        if ((endIndex - startIndex + 4) % 4 == 3) {
            return L;
        }
        return S;
    }
}
