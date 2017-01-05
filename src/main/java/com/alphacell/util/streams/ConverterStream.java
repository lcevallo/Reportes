package com.alphacell.util.streams;

import org.apache.poi.ss.usermodel.Row;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ConverterStream {

	public static <T> Stream<T> asStream(Iterator<T> sourceIterator) {
		return asStream(sourceIterator, false);
	}

	public static <T> Stream<T> asStream(Iterator<T> sourceIterator, boolean parallel) {
		Iterable<T> iterable = () -> sourceIterator;
		return StreamSupport.stream(iterable.spliterator(), parallel);
	}


    public static Stream<Row> asStreamRow(Iterator<Row> rowIterator) {

            return asStream(rowIterator, false);
    }
}
