package com.fayden.advent_of_code.common;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LineTest {

    static Line horizontalPoints() {
        return new Line(new Point(10, 0), new Point(5, 0));
    }

    static Line singlePoints() {
        return new Line(new Point(10, 10), new Point(10, 10));
    }

    static Line verticalPoints() {
        return new Line(new Point(0, 10), new Point(0, 5));
    }

    static Line diagonalPoints() {
        return new Line(new Point(1, 5), new Point(5, 1));
    }

    static Line anyPoints() {
        return new Line(new Point(1, 5), new Point(6, 32));
    }

    @Nested
    class TestGetSegment {
        @Test
        void whenVertical() {
            // GIVEN
            final Line line = verticalPoints();
            // WHEN
            final Stream<Point> segment = line.getSegment();
            // THEN
            assertThat(segment)
                    .hasSize(6)
                    .containsExactlyInAnyOrder(
                            new Point(0, 5),
                            new Point(0, 6),
                            new Point(0, 7),
                            new Point(0, 8),
                            new Point(0, 9),
                            new Point(0, 10)
                    );
        }

        @Test
        void whenHorizontal() {
            // GIVEN
            final Line line = horizontalPoints();
            // WHEN
            final Stream<Point> segment = line.getSegment();
            // THEN
            assertThat(segment)
                    .hasSize(6)
                    .containsExactlyInAnyOrder(
                            new Point(5, 0),
                            new Point(6, 0),
                            new Point(7, 0),
                            new Point(8, 0),
                            new Point(9, 0),
                            new Point(10, 0)
                    );
        }

        @Test
        void whenNotHorizontalAndNotVertical() {
            // GIVEN
            final Line line = anyPoints();
            // WHEN
            final Stream<Point> segment = line.getSegment();
            // THEN
            assertThat(segment).isEmpty();
        }

        @Test
        void whenSinglePoint() {
            // GIVEN
            final Line line = singlePoints();
            // WHEN
            final Stream<Point> segment = line.getSegment();
            // THEN
            assertThat(segment).hasSize(1)
                    .containsExactly(new Point(10, 10));
        }

        @Test
        void whenDiagonal() {
            // GIVEN
            final Point point1 = new Point(1, 1);
            final Point point2 = new Point(3, 3);
            final Line line = new Line(point1, point2);
            // WHEN
            final Stream<Point> segment = line.getSegment();
            // THEN
            assertThat(segment).hasSize(3)
                    .containsExactly(
                            new Point(1, 1),
                            new Point(2, 2),
                            new Point(3, 3)
                    );
        }

        @Test
        void whenDiagonal2() {
            // GIVEN
            final Line line = diagonalPoints();
            // WHEN
            final Stream<Point> segment = line.getSegment();
            // THEN
            assertThat(segment).hasSize(5)
                    .containsExactly(
                            new Point(1, 5),
                            new Point(2, 4),
                            new Point(3, 3),
                            new Point(4, 2),
                            new Point(5, 1)
                    );
        }
    }

    @Nested
    class TestCreateLine {
        @Test
        void create_whenString() {
            // GIVEN
            String input = "0,9 -> 5,9";
            // WHen
            final Line horizontalOrVerticalLine = new Line(input);
            // THEN
            assertThat(horizontalOrVerticalLine.getPoint1()).isEqualTo(new Point(0, 9));
            assertThat(horizontalOrVerticalLine.getPoint2()).isEqualTo(new Point(5, 9));
        }

        @Test
        void create2_whenString() {
            // GIVEN
            String input = "136,639 -> 382,639";
            // WHen
            final Line horizontalOrVerticalLine = new Line(input);
            // THEN
            assertThat(horizontalOrVerticalLine.getPoint1()).isEqualTo(new Point(136, 639));
            assertThat(horizontalOrVerticalLine.getPoint2()).isEqualTo(new Point(382, 639));
        }

        @Test
        void create_whenPoint() {
            // GIVEN
            Point point1 = new Point(136, 639);
            Point point2 = new Point(382, 639);
            // WHen
            final Line horizontalOrVerticalLine = new Line(point1, point2);
            // THEN
            assertThat(horizontalOrVerticalLine.getPoint1()).isEqualTo(point1);
            assertThat(horizontalOrVerticalLine.getPoint2()).isEqualTo(point2);
        }
    }

    @Nested
    class TestIsVertical {
        static private Stream<Arguments> whenOtherShouldReturnFalse() {
            return Stream.of(
                    Arguments.of(horizontalPoints()),
                    Arguments.of(diagonalPoints()),
                    Arguments.of(anyPoints())
            );
        }

        @Test
        void whenVertical() {
            // GIVEN
            final Line line = verticalPoints();
            // WHEN
            final boolean result = line.isVertical();
            // THEN
            assertThat(result).isTrue();
        }

        @Test
        void whenSinglePoint() {
            // GIVEN
            final Line line = singlePoints();
            // WHEN
            final boolean result = line.isVertical();
            // THEN
            assertThat(result).isTrue();
        }

        @ParameterizedTest
        @MethodSource
        void whenOtherShouldReturnFalse(Line line) {
            // WHEN
            final boolean result = line.isVertical();
            // THEN
            assertThat(result).isFalse();
        }
    }

    @Nested
    class TestIsHorizontal {
        static private Stream<Arguments> whenOtherShouldReturnFalse() {
            return Stream.of(
                    Arguments.of(verticalPoints()),
                    Arguments.of(diagonalPoints()),
                    Arguments.of(anyPoints())
            );
        }

        @Test
        void whenHorizontal() {
            // GIVEN
            final Line line = horizontalPoints();
            // WHEN
            final boolean result = line.isHorizontal();
            // THEN
            assertThat(result).isTrue();
        }

        @Test
        void whenSinglePoint() {
            // GIVEN
            final Line line = singlePoints();
            // WHEN
            final boolean result = line.isHorizontal();
            // THEN
            assertThat(result).isTrue();
        }

        @ParameterizedTest
        @MethodSource
        void whenOtherShouldReturnFalse(Line line) {
            // WHEN
            final boolean result = line.isHorizontal();
            // THEN
            assertThat(result).isFalse();
        }
    }

    @Nested
    class TestIsDiagonal {
        static private Stream<Arguments> whenOtherShouldReturnFalse() {
            return Stream.of(
                    Arguments.of(verticalPoints()),
                    Arguments.of(horizontalPoints()),
                    Arguments.of(anyPoints())
            );
        }

        @Test
        void whenHorizontal() {
            // GIVEN
            final Line line = diagonalPoints();
            // WHEN
            final boolean result = line.isDiagonal();
            // THEN
            assertThat(result).isTrue();
        }

        @Test
        void whenSinglePoint() {
            // GIVEN
            final Line line = singlePoints();
            // WHEN
            final boolean result = line.isDiagonal();
            // THEN
            assertThat(result).isTrue();
        }

        @ParameterizedTest
        @MethodSource
        void whenOtherShouldReturnFalse(Line line) {
            // WHEN
            final boolean result = line.isDiagonal();
            // THEN
            assertThat(result).isFalse();
        }
    }
}