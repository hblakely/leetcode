package utilities

import (
	"fmt"
	"reflect"
	"sort"
)

type Stack struct {
	items []interface{}
	typ   interface{}
}

func (s *Stack) Push(item interface{}) {
	if s.typ == nil {
		s.typ = item
	} else if reflect.TypeOf(s.typ) != reflect.TypeOf(item) {
		panic("Cannot push item of different type to the stack")
	}
	s.items = append(s.items, item)
}

func (s *Stack) PushFirst(item interface{}) {
	if s.typ == nil {
		s.typ = item
	} else if reflect.TypeOf(s.typ) != reflect.TypeOf(item) {
		panic("Cannot push item of different type to the stack")
	}

	s.items = append([]interface{}{item}, s.items...)
}

func (s *Stack) PushLast(item interface{}) {
	if s.typ == nil {
		s.typ = item
	} else if reflect.TypeOf(s.typ) != reflect.TypeOf(item) {
		panic("Cannot push item of different type to the stack")
	}

	s.items = append(s.items, item)
}

func (s *Stack) Pop() (interface{}, bool) {
	return s.PopLast()
}

func (s *Stack) PopFirst() (interface{}, bool) {
	if len(s.items) == 0 {
		return nil, false
	}
	item := s.items[0]
	s.items = s.items[1:]
	return item, true
}

func (s *Stack) PopLast() (interface{}, bool) {
	if len(s.items) == 0 {
		return nil, false
	}
	lastIndex := len(s.items) - 1
	item := s.items[lastIndex]
	s.items = s.items[:lastIndex]
	return item, true
}

func (s *Stack) IndexOf(item interface{}) int {
	for i, val := range s.items {
		if val == item {
			return i
		}
	}
	return -1
}

func (s *Stack) Get(index int) interface{} {
	if index < 0 || index > len(s.items) {
		panic(fmt.Sprintf("Index Out Of Bounds: %d", index))
	}
	return s.items[index]
}

func (s *Stack) Remove(index int) interface{} {
	if index < 0 || index >= len(s.items) {
		return nil
	}

	item := s.items[index]
	if index == len(s.items)-1 {
		// If the item to be removed is the last element,
		// simply truncate the slice
		s.items = s.items[:index]
	} else {
		// Remove the item by combining the elements before and after the index
		s.items = append(s.items[:index], s.items[index+1:]...)
	}

	return item
}

func (s *Stack) Sort() {
	sort.Slice(s.items, func(i, j int) bool {
		return fmt.Sprintf("%v", s.items[i]) < fmt.Sprintf("%v", s.items[j])
	})
}
