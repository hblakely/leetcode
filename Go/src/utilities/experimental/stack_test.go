package utilities

import (
	"reflect"
	"testing"
)

func TestStack(t *testing.T) {
	stack := Stack{}

	stack.Push(1)
	stack.Push(2)
	stack.Push(3)

	item, _ := stack.Pop()
	if item != 3 {
		t.Errorf("Expected 3, got %d", item)
	}

	stack.Push(4)
	stack.Push(5)

	item, _ = stack.PopFirst()
	if item != 1 {
		t.Errorf("Expected 1, got %d", item)
	}

	item, _ = stack.PopLast()
	if item != 5 {
		t.Errorf("Expected 5, got %d", item)
	}

	stack.Push(2)

	if stack.IndexOf(2) != 1 {
		t.Errorf("Expected index of 2 to be 1, got %d", stack.IndexOf(2))
	}

	stack.Sort()

	if !reflect.DeepEqual(stack.items, []int{2, 3, 4}) {
		t.Errorf("Expected [2 3 4], got %v", stack.items)
	}
}
