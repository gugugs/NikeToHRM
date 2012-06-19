package controller;

public class TargetWord {
	private String word;
	private int currentRightLetterNumber;
	private int targetRightLetter;
	private int startPosition;
	private int endPosition;
	private boolean findStatus;

	public TargetWord(String word) {
		this.word = word;
		this.currentRightLetterNumber = 0;
		this.targetRightLetter = word.length() - 1;
		this.findStatus = false;
	}

	public int getCurrentRightLetter() {
		return this.word.toCharArray()[currentRightLetterNumber];
	}

	public boolean increaseCurrentRightLetterNumber() {
		if (currentRightLetterNumber == targetRightLetter) {
			this.findStatus = true;
			return true;
		} else {
			currentRightLetterNumber++;
			return false;
		}
	}

	public boolean getFindStatus() {
		return this.findStatus;
	}

	public void setFindStatus(boolean status) {
		this.findStatus = status;
	}

	public String getWord() {
		return this.word;
	}

	public void reset() {
		this.currentRightLetterNumber = 0;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	public int getEndPosition() {
		return endPosition;
	}

	public void setEndPosition(int endPosition) {
		this.endPosition = endPosition;
	}
}
