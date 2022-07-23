package com.example.project.util;

public final class SqlMapper {
	public static final String UNREACHABLE_CODE = "Unreachable! =exists= should return only 0 or 1";

	public static boolean existsResultToBool(int result) {
		boolean out;
		switch (result) {
			case 0:
				out = false;
				break;
			case 1:
				out = true;
				break;
			default:
				throw new IllegalStateException(UNREACHABLE_CODE);
		}

		return out;
	}

	private SqlMapper() {}
}
